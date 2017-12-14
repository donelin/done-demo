function(){
	var	collection={
		age:this.age,
		paint:{},
		sing: {},
		basketball: {},
		dance:{},
		eat:{},
		run:{}
    }
	if(this.instrest){
		this.instrest.forEach(function(ins){
			if(ins==="paint"){
				collection.paint.put(this.name);
			}else if(ins==="sing"){
				collection.sing.put(this.name);
			}else if(ins==="basketball"){
				collection.basketball.put(this.name);
			}else if(ins==="dance"){
				collection.dance.put(this.name);
			}else if(ins==="eat"){
				collection.eat.put(this.name);
			}else if(ins==="run"){
				collection.run.put(this.name);
			}
		})
	}
	
	array[this.age] = collection;

	emit(this.age,array);
} 