'use strict';

angular.module('myApp')
	.config(function ($stateProvider){
		$stateProvider.state('user-management',{
			parent: 'admin',
            url: '/user-management',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/admin/user/user-management.html'
                }
            }
		})
	});