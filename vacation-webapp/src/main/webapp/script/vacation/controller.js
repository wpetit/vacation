// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers
		.controller('VacationsCtrl',
				function($scope, $location, vacationService) {
					// Refresh the grid, calling the appropriate service method.
					$scope.refresh = function() {
						var page = $scope.pagingOptions.currentPage;
						vacationService.getAll(
								($scope.pagingOptions.pageSize * (page - 1)),
								5, $scope.sortOptions.field,
								$scope.sortOptions.direction).success(
								function(data) {
									$scope.modelList = data.modelList;
									$scope.nbResults = data.total;
								});
					};

					$scope.pagingOptions = {
						pageSize : 5,
						currentPage : 1
					};

					// sort
					$scope.sortOptions = {
						field : "from",
						direction : "desc"
					};

					$scope.sortBy = function(attribute) {
						if ($scope.sortOptions.field != attribute) {
							$scope.sortOptions = {
								field : attribute,
								direction : "desc"
							};
						} else {
							if ($scope.sortOptions.direction == "desc") {
								$scope.sortOptions.direction = "asc";
							} else {
								$scope.sortOptions.direction = "desc";
							}
						}
					}

					$scope.$watch('sortOptions', function(newVal, oldVal) {
						if (newVal !== oldVal) {
							$scope.refresh();
						}
					}, true);

					$scope.goToCreateVacation = function() {
						$location.path('/vacation/create');
					};

					$scope.minDate = new Date();
					$scope.from = new Date();
					$scope.to = new Date();

					// Disable weekend selection
					$scope.disabled = function(date, mode) {
						return (mode === 'day' && (date.getDay() === 0 || date
								.getDay() === 6));
					};

					$scope.openFromDatePicker = function($event) {
						$event.preventDefault();
						$event.stopPropagation();
						$scope.fromDatePickerOpened = true;
					};
					
					$scope.openToDatePicker = function($event) {
						$event.preventDefault();
						$event.stopPropagation();
						$scope.toDatePickerOpened = true;
					};

					$scope.dateOptions = {
						formatYear : 'yy',
						startingDay : 1
					};

					$scope.initDate = new Date('2016-15-20');
					$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
							'dd.MM.yyyy', 'shortDate' ];
					$scope.format = $scope.formats[0];

					$scope.refresh();
				});
