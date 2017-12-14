function (key, values) {
    var collections = {};

    values.forEach(function (value) {
        if(value)
        {
            var collection;
            for (var key in value) {
                if (value.hasOwnProperty(key)) {
                	collection=value[key];
                }
                if (collection.sing) {
                	collection.flag=true;
                	collections[key]==collection;
                }
            }
        }
    });
    return collections;
}
