var monGrpModule = angular.module('monitoringGrpModule',['ui.bootstrap',
	'ngRoute','nativeUtil']);

monGrpModule.controller('monitoringGroupController',['$scope','$location','$http','$nativeAPI',

	function($scope,$location,$http,$nativeAPI){

		$scope.loadHome= function(){

			$location.url('/appHome');
		};
	}
]);//End of monitoringGroupController definition