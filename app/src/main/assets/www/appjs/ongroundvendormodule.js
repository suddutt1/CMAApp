var onGroundVendorModule = angular.module('onGroundVendorModule',['ui.bootstrap',
	'ngRoute','nativeUtil']);

onGroundVendorModule.controller('onGroundVendorController',['$scope','$location','$http','$nativeAPI',

	function($scope,$location,$http,$nativeAPI){

		$scope.loadHome=function(){

			$location.url("/appHome");
		};
	}
]);//End of onGroundVendorController definition