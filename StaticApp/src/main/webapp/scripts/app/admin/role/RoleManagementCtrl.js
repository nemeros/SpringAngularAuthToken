'use strict';

angular.module('myApp')
    .controller('RoleManagementCtrl', function ($scope, Role) {
        $scope.roles = [];
       
        $scope.loadAll = function () {
        	Role.query().$promise.then(function (result, headers) {
                $scope.roles = result;
            });
        };

        $scope.loadAll();
    });
