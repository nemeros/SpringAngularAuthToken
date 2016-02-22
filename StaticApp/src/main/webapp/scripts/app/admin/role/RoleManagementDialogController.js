'use sctrict';


angular.module('myApp')
.controller('RoleManagementDialogController',
	    ['$scope', '$stateParams', '$uibModalInstance', 'roleEntity', 'Role',
	        function($scope, $stateParams, $uibModalInstance, roleEntity, Role) {

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
	            if ($scope.user.id != null) {
	                Role.update($scope.role, onSaveSuccess, onSaveError);
	            } else {
	                Role.save($scope.role, onSaveSuccess, onSaveError);
	            }
	        };

	        $scope.clear = function() {
	            $uibModalInstance.dismiss('cancel');
	        };
	}]);