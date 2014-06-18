//sort filter
vacationAppFilters.filter('sortIconFilter', function() {
	return function(input, sortOptions) {
		var icon = "";
		if (sortOptions.field == input) {
			if ("desc" == sortOptions.direction) {
				icon = '\u25BC';
			} else {
				icon = '\u25B2';
			}
		}
		return icon;
	}
});