import org.springframework.web.multipart.MultipartHttpServletRequest

class PhotoController {

    def view = {
    		[photoList:Photo.listOrderById(order:'desc',newPhoto:new Photo()),curAction:params.curAction,curController:params.curController]
    }
    
    def save = {
    		def photo = Photo.findById(params.id)
    		photo.setCaption(params.caption)
    		photo.save()
    }
    
    def delete = {
    		def photoInstance = Photo.get(params.id)
    		if(photoInstance.getName()){
    			def photoFile = grailsAttributes.getApplicationContext().getResource("/images/${photoInstance.getName()}").getFile()
    			photoFile.delete()
    		}
    		photoInstance.delete()				
    }

    def add = {
    		def photo = new Photo(id:params.id,url:params.url,caption:params.caption)
    		
    		photo.save()
    		
    		redirect(controller:params.curController,action:params.curAction)
    }
    
    def upload = {
    		def maxPhoto = Photo.list(sort:'id',order:'desc',max:'1')
    		def newId
    		
    		if(!maxPhoto)
    			newId = 1
    		else
    			newId = maxPhoto[0].getId() + 1
    			
    		def downloadedFile = request.getFile("fileUpload")
    		
    		if(downloadedFile && !downloadedFile.empty){
    			def name = "${newId}.jpg"
    			def filePath = grailsAttributes.getApplicationContext().getResource("/images").getFile().toString() + '/'+name
    			downloadedFile.transferTo(new File(filePath))
    			def photoInstance = new Photo(name:name,caption:params.caption)
    			photoInstance.save()
    		}
    		
    		redirect(controller:params.curController,action:params.curAction)
    }
}
