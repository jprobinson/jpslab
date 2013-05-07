class BootStrap {

     def init = { servletContext ->
     
  		if(!User.get(1)){
  			new User(firstName:'JP', lastName:'Robinson', login:'jp', password:'jp').save()
  		}
  		if(!Creation.findByName('The Amazing Editable Box!')){
  			new Creation(name:'The Amazing Editable Box!', description:'desc', date:new Date(),link:'').save()
  		}
     }
     def destroy = {
     }
} 