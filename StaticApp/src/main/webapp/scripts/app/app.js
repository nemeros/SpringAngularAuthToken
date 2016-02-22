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
		function getKey(key){return $localStorageProvider.get(key)};
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		$httpProvider.defaults.headers.common["X-AUTH-TOKEN"] = getKey('token');
		
		
		function b(a){return a?(a^Math.random()*16>>a/4).toString(16):([1e16]+1e16).replace(/[01]/g,b)};
		
		
		
		$httpProvider.interceptors.push(function() {
	        return {
	            'request': function(response) {
	                // put a new random secret into our CSRF-TOKEN Cookie before each request
	                document.cookie = 'XSRF-TOKEN=' + b();
	                return response;
	            }
	        };
		});
	});



