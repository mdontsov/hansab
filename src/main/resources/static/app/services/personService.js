angular.module('app').service('PersonService', function($http) {
    this.getAllPersons = function() {
        // Fetch persons from Spring Boot backend
        return $http.get('/persons');
    };
});
