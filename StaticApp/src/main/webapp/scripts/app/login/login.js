'use strict';

angular.module('myApp')
	.config(function ($stateProvider){
		$stateProvider.state('login-management',{
			parent: 'site',
            url: '/login',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/login/login.html',
                    controller: 'loginController'
                }
            }
		})
	});