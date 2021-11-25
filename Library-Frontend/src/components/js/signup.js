import axios from 'axios'
import JQuery from 'jquery'
let $ = JQuery
var config = require ('../../../config')

var backendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
    }
};

var frontendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.host + ':' + config.dev.port;
        case 'production':
            return 'https://' + config.build.host + ':' + config.build.port ;
    }
};
var backendUrl = backendConfigurer();
var frontendUrl = frontendConfigurer();

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name:'member_login',
    data () {
        return {
            user: '',
            username: '',
            password: '',
            errorLogin: '',
            response: []
        }
    },
    methods: {
        /**
         * @author alymo
         * @param username
         * @param password
         * gets user input from frontend and logs in using controller method login
         */
        login (username, password, member_type, member_status) {
            AXIOS.post('/member_sign_up/', $.param({username: username, password: password, member_type: member_type, member_status: member_status}))
                .then(response => {
                    this.user = response.data

                    if (response.status===200) {

                        window.localStorage.setItem('username', this.user.username)

                        if(this.type.localeCompare("member")==0){

                            window.location.href = "/#/member"
                        }
                        else if(this.type.localeCompare("librarian")==0){
                            window.location.href = "/#/librarian"
                        }
                        else {
                            window.location.href = "/#/headLibrarian"
                        }

                        location.reload();
                    }
                })
                .catch(e => {
                    swal("ERROR", e.response.data, "error");
                })
        }
    }

}