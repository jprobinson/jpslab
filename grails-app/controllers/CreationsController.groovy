class CreationsController {

	def index = {
			render(view:'edit',model:[creationList : Creation.listOrderByDate(order:'desc')] )
	}
    def list = { 	
    		[creationList : Creation.listOrderByDate(order:'desc')] 
    }
    
    def edit = {
    		render(view:'edit', model:[creation:Creation.findById(params.id)])
    }
    
    def delete = {
    		def creationInstance = Creation.get(params.id)
    		creationInstance.delete()
    }
    
    def add = {
    		render(view:'edit', model:[creation:new Creation()])
    }
    
    def save = {

    		def creationInstance = Creation.get( params.id )
    		if(creationInstance==null){
    			creationInstance = new Creation(id:params.id,
    								description:params.description,
    								name:params.name,
    								link:params.name.replaceAll(/\s/,'_'),
    								date:new Date())
    		}
    		
            creationInstance.properties = params
            creationInstance.save()
    }
	
	def boxes = {
			render(view:'boxes')
	}

}
