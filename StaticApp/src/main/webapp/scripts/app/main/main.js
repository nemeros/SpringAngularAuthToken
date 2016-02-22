'use strict';

angular.module('myApp')
	.config(function ($stateProvider){
		$stateProvider.state('main',{
			parent: 'site',
            url: '/',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
		})
	});