// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers.controller('VacationTypeCtrl', function($scope,
		$location, vacationService, vacationTypeService) {

	// initialization
	$scope.beginDate = new Date();
	$scope.endDate = new Date();
	$scope.dateFormat = 'dd/MM/yyyy';
	$scope.numberOfDays = 1;
	// sort
	$scope.sortOptions = {
		field : "type",
		direction : "asc"
	};

	$scope.openEndDatePicker = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.endDatePickerOpened = true;
	};

	$scope.openBeginDatePicker = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.beginDatePickerOpened = true;
	};

	// Refresh the grid, calling the appropriate service method.
	$scope.save = function() {
		vacationTypeService.saveVacationType($scope.typeName, $scope.beginDate,
				$scope.endDate, $scope.numberOfDays).success(function(data) {
			$scope.typeName = null;
			$scope.beginDate = new Date();
			$scope.endDate = new Date();
			$scope.numberOfDays = 1;
		});
	};

	$scope.cancel = function() {
		$location.path('/vacationTypes/list');
	};

	$scope.getAll = function() {
		vacationTypeService.getVacationTypeList($scope.sortOptions.field,
				$scope.sortOptions.direction).success(function(data) {
			$scope.vacationTypes = data;
		});
	};

	$scope.sortBy = function(attribute) {
		if ($scope.sortOptions.field != attribute) {
			$scope.sortOptions = {
				field : attribute,
				direction : "asc"
			};
		} else {
			if ($scope.sortOptions.direction == "desc") {
				$scope.sortOptions.direction = "asc";
			} else {
				$scope.sortOptions.direction = "desc";
			}
		}
	};

	$scope.$watch('sortOptions', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			$scope.getAll();
		}
	}, true);

	$scope.$watch('vacationTypes', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			$scope.getVacationTypesBalances();
		}
	}, true);

	$scope.goToCreateVacationType = function() {
		$location.path('/vacationTypes/create');
	};

	$scope.getVacationTypesBalances = function() {
		$scope.numberOfVacations = new Array();
		$scope.vacationTypes.forEach(function(vacationType) {
			$scope.retrieveVacationCount(vacationType.id, vacationType.type,
					vacationType.numberOfDays);
		});
	};

	$scope.retrieveVacationCount = function(vacationTypeId, vacationTypeName,
			max) {
		vacationService.getNumberOfVacations(vacationTypeId).success(
				function(data) {
					$scope.numberOfVacations.push({
						vacationTypeId : vacationTypeId,
						vacationTypeName : vacationTypeName,
						max : max,
						count : data
					});
				});
	};

	$scope.getAll();
});
