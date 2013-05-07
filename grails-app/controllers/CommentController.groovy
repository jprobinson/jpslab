class CommentController {

    def add = {
    		def comment = "news".equalsIgnoreCase(params.objectName) ? new NewsComment() : new CreationComment()
    		
    		render(view:'/comment/edit', model:[objectId:params.objectId,objectName:params.objectName,comment:comment])
    }

    def delete = {
    		def comment
    		if("CreationComment".equalsIgnoreCase(params.className))
    			comment = CreationComment.get(params.id)
    		else
    			comment = NewsComment.get(params.id)
    			
    		comment.delete()		
    }
    
    
    def save = {
    		def parentObject
    		def commentInstance
    		
    		if("news".equalsIgnoreCase(params.objectName)){
    			parentObject = News.findById(params.objectId)
    			commentInstance = NewsComment.get( params.id )
    			if(commentInstance==null){
        			commentInstance = new NewsComment(id:params.id,
        								text:params.text,
        								commentor:params.commentor,
        								date:new Date(),
        								news:parentObject)
        		}	
    		}
    		else if("creation".equalsIgnoreCase(params.objectName)){
    			parentObject = Creation.findById(params.objectId)

    			commentInstance = CreationComment.get( params.id )
    			if(commentInstance==null){
        			commentInstance = new CreationComment(id:params.id,
        								text:params.text,
        								commentor:params.commentor,
        								date:new Date(),
        								creation:parentObject)
        			
        		}		
    		}

			parentObject.addToComments(commentInstance)
			parentObject.save()
			commentInstance.save()

					
		sendMail {
				to "john.paul.robinson@gmail.com"
				subject "A new comment on JP's Lab!"
				html """
					<h3>Hey Buddy,</h3>

					<h4>We just got a new comment.  Here's the info:</h4>
					
					<p>Comment By:<br/> ${commentInstance.getCommentor()}</p>
						
					<p>Comment Text: <br/> ${commentInstance.getText()}</p>
						
					<h4>Hope it was good news!</h4>
					
					<h4>Your pal,</h4>
					
					<h3>-The Lab</h3>
				"""
		}
    }


}

