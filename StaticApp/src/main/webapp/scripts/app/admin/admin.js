'use strict';

angular.module('myApp')
	.config(function ($stateProvider){
		$stateProvider.state('admin',{
			abstract: true,
			parent: 'site'			
		})
	});