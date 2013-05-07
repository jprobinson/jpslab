import grails.converters.*

class LoginController {

	def show = {
			render(view:'/login/login')
	}
    def login = { 
    		def user = User.findWhere(login:params.login,password:params.password)
    		session.user = user;

    		if(user){
    			render user as JSON
    		}
    		else{
    			return 'fail'
    		}
    }
	
	def logout = {
			session.user = null
	}
}
