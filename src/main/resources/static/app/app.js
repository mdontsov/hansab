// Define the AngularJS app module
angular.module('app', [])
.controller('MainController', function($scope, PersonService, CarService) {
    $scope.persons = [];
    $scope.cars = [];
    $scope.filteredPersons = [];
    $scope.filteredCars = [];

    $scope.personSearchQuery = '';
    $scope.carSearchQuery = '';

    // Sorting parameters
    $scope.personSortDirection = 'asc'; // default sort direction for persons
    $scope.carSortDirection = 'asc'; // default sort direction for cars

    // Fetch all persons when the page loads
    PersonService.getAllPersons().then(function(response) {
        $scope.persons = response.data;
        $scope.filteredPersons = $scope.persons;
    });

    // Fetch all cars when the page loads
    CarService.getAllCars().then(function(response) {
        $scope.cars = response.data;
        $scope.filteredCars = $scope.cars;
    });

    // Search persons
    $scope.searchPersons = function() {
        let persons = $scope.persons;
        if ($scope.personSearchQuery) {
            persons = persons.filter(person => person.name.toLowerCase().includes($scope.personSearchQuery.toLowerCase()));
        }
        $scope.filteredPersons = persons;
    };

    // Search cars
    $scope.searchCars = function() {
        let cars = $scope.cars;
        if ($scope.carSearchQuery) {
            cars = cars.filter(car =>
                car.make.toLowerCase().includes($scope.carSearchQuery.toLowerCase()) ||
                car.model.toLowerCase().includes($scope.carSearchQuery.toLowerCase()) ||
                car.numberplate.toLowerCase().includes($scope.carSearchQuery.toLowerCase())
            );
        }
        $scope.filteredCars = cars;
    };

    // Sort persons by name
    $scope.sortPersons = function() {
        $scope.filteredPersons = $scope.filteredPersons.sort((a, b) => {
            if ($scope.personSortDirection === 'asc') {
                return a.name.localeCompare(b.name);
            } else {
                return b.name.localeCompare(a.name);
            }
        });
    };

    // Sort cars by numberplate
    $scope.sortCars = function() {
        $scope.filteredCars = $scope.filteredCars.sort((a, b) => {
            if ($scope.carSortDirection === 'asc') {
                return a.numberplate.localeCompare(b.numberplate);
            } else {
                return b.numberplate.localeCompare(a.numberplate);
            }
        });
    };
})

// Service to fetch persons
.service('PersonService', function($http) {
    this.getAllPersons = function() {
        return $http.get('/persons');
    };
})

// Service to fetch cars
.service('CarService', function($http) {
    this.getAllCars = function() {
        return $http.get('/cars');
    };
});
