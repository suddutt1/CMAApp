//navbar Module
var navBarModule = angular.module('navBarModule', ['ui.bootstrap','nativeUtil',
	'ngRoute']);
navBarModule.controller('navBarController',['$scope','$location','$http','$nativeAPI',
	function($scope,$location,$http,$nativeAPI){

		$scope.exitApp = function(){
			$scope.navCollapsed = true;
			$nativeAPI.exitApp();			
		};


	}]);