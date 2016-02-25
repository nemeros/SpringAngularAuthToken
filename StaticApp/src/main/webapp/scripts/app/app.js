'use strict';

angular.module('myApp', ['ngResource', 'ui.router', 'ui.bootstrap', 'ngStorage'])

	//route config
	.config(function($stateProvider, $urlRouterProvider){
		$urlRouterProvider.otherwise('/');
		
		$stateProvider.state('site', {
            'abstract': true,
            views: {
                'navbar@': {
                    templateUrl: 'scripts/components/navbar/navbar.html',
                    controller: 'NavbarController'
                }
            }
        });
	})
	.config(function($httpProvider, $localStorageProvider){
		$httpProvider.defaults.headers.common['X-AUTH-TOKEN'] = $localStorageProvider.get('jwtAuth');
	});



