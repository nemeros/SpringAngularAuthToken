'use strict';

angular.module('myApp')
    .controller('RoleManagementCtrl', function ($scope, Role) {
        $scope.roles = [];
        $scope.error = null;
        
        $scope.loadAll = function () {
        	Role.query().$promise.then(function (result, headers) {
                $scope.roles = result;
            }, function(error){
            	$scope.error = error.data.message;
            });
        };

        $scope.loadAll();
    });
