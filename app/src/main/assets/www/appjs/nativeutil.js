var nativeUtil = angular.module('nativeUtil',[]);
nativeUtil.service('$nativeAPI',function(){

	this.APP_DATA = {};
	this.initialized = false;
	this.init=function(){
		if(this.initialized === false){
    	if(typeof Android === "undefined"){
		            console.log('Android not found');
		            this._APP_DATA  = {'config': {'deviceId':'TEST_DEV','userType':'none','userId':''}};
		    	}
		    else{
		    		console.log('Android is defined');
		    		 this._APP_DATA = JSON.parse(Android.getStoredDataFromLocal());

		            if(typeof this._APP_DATA["config"] === "undefined")
		            {
		                //Storing a default value
		                this._APP_DATA  = {'config': {'deviceId':Android.getDeviceId(),'userType':'none','userId':''}};
		                var dataToStore = JSON.stringify(this._APP_DATA);
		                var result = Android.saveDataInLocal(dataToStore);
		                Android.showAlert("Default configuration result "+result);
		            }
		            else
		            {
		                Android.showAlert("Loaded existing configuration from the local file");
		            }
		    }
		    this.initialized = true;
		}
	};
	this.exitApp  = function(){
	    if(typeof Android === "undefined")
	    {

	    }
	    else
	    {
	        Android.exitApplication();
	    }
	};
	this.showAlert = function(text){
		
		if(typeof Android === "undefined")
	    {
	    	alert(text);
	    }
	    else
	    {
	        Android.showAlert(text);
	    }	
	};
	this.storeInConfig= function(key,value){
		this.init();
		this._APP_DATA['config'][key] =value;
		console.log(this.APP_DATA['config']); 
	};
	this.getConfig = function(key){
		this.init();
		return this._APP_DATA['config'][key];
			
	};

	this.saveConfig=function(){
		this.init();
		if(typeof Android === "undefined"){
			
		}
		else{
            var dataToStore = JSON.stringify(this._APP_DATA);
            var result = Android.saveDataInLocal(dataToStore);
            Android.showAlert("Configuration save result "+result);
		}
	};
});