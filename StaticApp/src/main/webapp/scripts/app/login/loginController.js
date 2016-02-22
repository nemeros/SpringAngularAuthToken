'use strict';

angular.module('myApp')
.controller('loginController', function ($scope, $http, $state, $log, $localStorage) {
    $scope.error = null;
   
    $scope.submit = function () {
    	
    	if($scope.loginForm.$invalid){
    		$scope.error = $scope.loginForm.$error;
    	}else{
    		var data = new Object();
    		data.nom = $scope.name;
    		data.password = $scope.password;
    		
	    	$http({
	    		method: 'POST',
	    		data: data,
	    		url: 'http://localhost:8080/auth'
	    	}).then(function succesCallback(response){
	    		$localStorage.token = response.data.token;
	    		$scope.error = null;
	    		$state.go('main', null, {reload: true});
	    	}, function errorCallback(response){
	    		$scope.error = response;
	    	})
    	}
    };
});
