class JpslabController {
	def newsService
	def creationService
	    
    def index = {
    		redirect(url:"http://jpslab.tumblr.com")
    }
    
	def about = {
    		[aboutMeList : AboutMe.list()] 
    }    
	
    def creations = {
			def creationsList = []
    		if(params.creationLink!=null)
    			creationsList = creationService.findCreationDirectLink(params.year as int, params.month as int, params.day as int, params.creationLink)
    		else
    			creationsList = creationService.getCreationList(params)
			[creationsList : creationsList, editableBox : Creation.findByName('The Amazing Editable Box!')]
	}
	
}
