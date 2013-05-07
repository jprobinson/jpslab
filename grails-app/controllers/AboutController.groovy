class AboutController {

		def index = {
				render(view:'/about/edit',model:[aboutMeList : AboutMe.listOrderByDate(order:'desc')] )
		}
	    def list = { 	
	    		[aboutList : AboutMe.listOrderById(order:'desc')] 
	    }
	    
	    def edit = {
	    		render(view:'/about/edit', model:[aboutMe:AboutMe.get(params.id)])
	    }
	    
	    def delete = {
	    		def aboutInstance = AboutMe.get(params.id)
	    		aboutInstance.delete()
	    }
	    
	    def add = {
	    		render(view:'/about/edit', model:[aboutMe:new AboutMe()])
	    }
	    
	    def save = {
	    		def aboutInstance = AboutMe.get( params.id )
	    		if(aboutInstance==null){
	    			aboutInstance = new AboutMe(id:params.id,
	    								text:params.text)
	    		}
	            aboutInstance.properties = params
	            aboutInstance.save()        
	    }
	

}
