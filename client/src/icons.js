import Vue from 'vue'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faPlay, faSignOutAlt, faBars, faList, faSearch , faEye, faRedo/* icons you want to import */ } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add( faPlay, faSignOutAlt, faBars, faList, faSearch, faEye, faRedo
    /* imported icons */ 
)

Vue.component('font-awesome-icon', FontAwesomeIcon)