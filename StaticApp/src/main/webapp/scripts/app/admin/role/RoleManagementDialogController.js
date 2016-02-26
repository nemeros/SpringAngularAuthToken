'use sctrict';


angular.module('myApp')
.controller('RoleManagementDialogController',
	    ['$scope', '$log', '$stateParams', '$uibModalInstance', 'roleEntity', 'Role',
	        function($scope, $log, $stateParams, $uibModalInstance, roleEntity, Role) {

	    	$scope.error = null;
	    	
	        $scope.role = roleEntity;

	        var onSaveSuccess = function (result) {
	            $scope.isSaving = false;
	            $scope.error = null;
	            $uibModalInstance.close(result);
	        };

	        var onSaveError = function (result) {
	            $scope.isSaving = false;
	            $scope.error = result.data.message;
	        };

	        $scope.save = function () {
	            $scope.isSaving = true;
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