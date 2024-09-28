angular.module('app').service('CarService', function($http) {
    this.getAllCars = function() {
        // Fetch cars from Spring Boot backend
        return $http.get('/cars');
    };
});
