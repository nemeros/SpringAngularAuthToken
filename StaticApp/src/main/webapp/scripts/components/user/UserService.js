'use strict';

angular.module('myApp')
	.factory('User', function ($resource) {
	    return $resource('http://localhost:8080/api/user/:id', {}, {
	        'query': {method: 'GET', isArray: true},
	        'get': {
	            method: 'GET',
	            transformResponse: function (data) {
	                data = angular.fromJson(data);
	                return data;
	            }
	        },
	        'save': { method:'POST' },
	        'update': { method:'PUT' },
	        'delete':{ method:'DELETE'}
	    });
	});