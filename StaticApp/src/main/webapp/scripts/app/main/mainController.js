'use strict'

angular.module('myApp')
.controller('mainController', function ($scope, $localStorage, jwtHelper) {
	$scope.user = null;
	if($localStorage.jwtAuth){
		var tokenPayload = jwtHelper.decodeToken($localStorage.jwtAuth);
		$scope.user = new Object();
		$scope.user.name = tokenPayload.sub;
		$scope.user.roles = tokenPayload.roles;
	}	
});