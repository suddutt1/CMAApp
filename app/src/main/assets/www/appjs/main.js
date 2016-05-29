
var app = angular.module('cmaManagementApp', ['ui.bootstrap','nativeUtil',
	'ngRoute','serviceManagementModule','onGroundVendorModule','monitoringGrpModule']);

app.config(['$routeProvider',function($routeProvider){
		
	
		$routeProvider.
			when('/userLanding',
				{
						templateUrl: 'views/userlanding.html',
        				controller: 'userHomeController'
				}).
			when('/serviceManagementHome',
				{
						templateUrl: 'views/serviceManagementLanding.html',
						controller: 'serviceManagementController'
				}).
			when('/onGroundVendorHome',{
						templateUrl: 'views/onGroundVendorLanding.html',
						controller: 'onGroundVendorController'
			}).
			when('/monitoringGroupHome',{
						templateUrl: 'views/monitoringGroupLanding.html',
						controller: 'monitoringGroupController'
			}).
			when('/appHome',{
						templateUrl	: 'views/appHome.html',
						controller: 'appHomeController'
			}).
			otherwise({

				redirectTo : '/appHome'
			});
}]);
app.controller('appHomeController',['$scope','$location','$http','$nativeAPI',
	function($scope,$location,$http,$nativeAPI){
	    // var deviceId =  $nativeAPI.getConfig('deviceId');
		// $nativeAPI.showAlert('Device Id obtained ...'+ deviceId);

		$scope.loadServiceManagement = function(){

			$location.url('/serviceManagementHome');
		};
		$scope.loadOnGroundVendor = function(){

			$location.url('/onGroundVendorHome');
		};
		$scope.loadMonitoringGrp = function(){

			$location.url('/monitoringGroupHome');
		};

}]);//End of appHomeController defition
