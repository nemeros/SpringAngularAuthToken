'use strict';

angular.module('myApp')
    .controller('NavbarController', function ($scope, $http, $localStorage, $log) {
    	$scope.logout = function(){
    		delete $localStorage.jwtAuth;
    		$http.defaults.headers.common['X-AUTH-TOKEN'] = "";
    	};
    });