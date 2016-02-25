'use strict';

angular.module('myApp')
	.config(function ($stateProvider){
		$stateProvider.state('role-management',{
			parent: 'admin',
            url: '/role-management',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/admin/role/role-management.html',
                    controller: 'RoleManagementCtrl as RoleCtrl'
                }
            }
		})
		.state('role-management.new',{
			parent: 'role-management',
			url: '/new',
			onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal){
				$uibModal.open({
                    templateUrl: 'scripts/app/admin/role/role-management-dialog.html',
                    controller: 'RoleManagementDialogController',
                    size: 'lg',
                    resolve: {
                        roleEntity: function () {
                            return {
                                id: null, 
                                nom: null
                            };
                        }
                    }
                }).result.then(function(result) {
                    $state.go('role-management', null, { reload: true });
                }, function() {
                    $state.go('role-management');
                })
			}]
		})
	});