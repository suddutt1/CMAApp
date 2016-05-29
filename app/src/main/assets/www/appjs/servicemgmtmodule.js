var servieMgmtModule = angular.module('serviceManagementModule',['ui.bootstrap','ngRoute','nativeUtil']);

servieMgmtModule.config(['$routeProvider',function($routeProvider){

}]);
servieMgmtModule.controller('serviceManagementController',['$scope','$location','$http','$nativeAPI',

	function($scope,$location,$http,$nativeAPI){
		console.log("Loaded the serviceManagementController");
		$scope.loadHome = function(){

			$location.url("/appHome");
		};
	}
]);//End of serviceManagementController definition