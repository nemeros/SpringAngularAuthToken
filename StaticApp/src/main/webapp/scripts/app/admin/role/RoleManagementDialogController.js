'use sctrict';


angular.module('myApp')
.controller('RoleManagementDialogController',
	    ['$scope', '$log', '$stateParams', '$uibModalInstance', 'roleEntity', 'Role',
	        function($scope, $log, $stateParams, $uibModalInstance, roleEntity, Role) {

	        $scope.role = roleEntity;

	        var onSaveSuccess = function (result) {
	            $scope.isSaving = false;
	            $uibModalInstance.close(result);
	        };

	        var onSaveError = function (result) {
	            $scope.isSaving = false;
	        };

	        $scope.save = function () {
	            $scope.isSaving = true;
	            $log.info('role : ' + angular.toJson($scope.role));
	            $log.info('user : ' + angular.toJson($scope.user));
	            if ($scope.role.id != null) {
	                Role.update($scope.role, onSaveSuccess, onSaveError);
	            } else {
	                Role.save($scope.role, onSaveSuccess, onSaveError);
	            }
	        };

	        $scope.clear = function() {
	            $uibModalInstance.dismiss('cancel');
	        };
	}]);