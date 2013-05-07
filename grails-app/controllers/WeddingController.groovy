class WeddingController {

    def index = {
    	[registryLinks:RegistryLink.list(),hotelLinks:HotelLink.list(),venueLinks:VenueLink.list(),stLouisLinks:StLouisLink.list(),weddingComments:WeddingComment.listOrderByDate(order:'desc')]
    }
    
    def editLink = {
    		render(view:'/wedding/edit', model:[link:findLink(params),objectName:params.objectName])
    }
    
    def deleteLink = {
    		def linkInstance 
    		if(params.objectName.equals('hotel'))
    			linkInstance = HotelLink.get(params.id)
    		else if(params.objectName.equals('venue'))
    			linkInstance = VenueLink.get(params.id)
    		else if(params.objectName.equals('stLouis'))
    			linkInstance = StLouisLink.get(params.id)
    		else
    			linkInstance = RegistryLink.get(params.id)
    		
    		linkInstance.delete()
    }
    
    def addLink = {
    		def linkInstance
    		if(params.objectName.equals('hotel'))
				linkInstance = new HotelLink()
			else if(params.objectName.equals('venue'))
				linkInstance = new VenueLink()
			else if(params.objectName.equals('stLouis'))
				linkInstance = new StLouisLink()
			else
				linkInstance = new RegistryLink()
		
    		render(view:'/wedding/edit', model:[link:linkInstance,objectName:params.objectName])
    }
    
    def saveLink = {
    		def linkInstance = findLink(params)
    		if(linkInstance==null){
    			if(params.objectName.equals('hotel'))
    				linkInstance = new HotelLink(id:params.id,url:params.url,text:params.text,description:params.description)
    			else if(params.objectName.equals('venue'))
    				linkInstance = new VenueLink(id:params.id,url:params.url,text:params.text,description:params.description)
    			else if(params.objectName.equals('stLouis')) 
    				linkInstance = new StLouisLink(id:params.id,url:params.url,text:params.text,description:params.description)
    			else
    				linkInstance = new RegistryLink(id:params.id,url:params.url,text:params.text,description:params.description)
    		}
    		
            linkInstance.properties = params
            linkInstance.save()        
    }
    
    
    
    private WeddingLink findLink(params){
    	def linkInstance 
		if(params.objectName.equals('hotel'))
			linkInstance = HotelLink.get(params.id)
		else if(params.objectName.equals('venue'))
			linkInstance = VenueLink.get(params.id)
    	else if(params.objectName.equals('stLouis'))
    		linkInstance = StLouisLink.get(params.id)
    	else
   			linkInstance = RegistryLink.get(params.id)
		
		linkInstance
    }
    
    def deleteComment = {
    	def comment= WeddingComment.get(params.id)

    	comment.delete()		
    }
    
    def saveComment = {
    	if(!params.text.contains('<a') && !params.text.contains('[link') && !params.text.contains('[url')){
    	
    		def	commentInstance = new WeddingComment(text:params.text,
        						commentor:params.commentor,
        						date:new Date())      	
    	
    		commentInstance.save()

			try{		
				sendMail {
					to "john.paul.robinson@gmail.com","kelseyhiggins@hotmail.com"
					subject "A new comment on the Wedding Site!"
					html """
					<h3>Hey Gang,</h3>

					<h4>We just got a new comment.  Here's the info:</h4>
					
					<p>Comment By:<br/> ${commentInstance.getCommentor()}</p>
					
					<p>Comment Text: <br/> ${commentInstance.getText()}</p>
					
					<h4>Hope it was a good one!</h4>
					
					<h4>Your pal,</h4>
					
					<h3>-The Lab</h3>
					"""
			}
			}
    		catch(Exception e){}
    	}
		forward(controller:"wedding",action:"index") 
    }
}
