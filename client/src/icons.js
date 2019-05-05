import Vue from 'vue'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faPlay/* icons you want to import */ } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add( faPlay
    /* imported icons */ 
)

Vue.component('font-awesome-icon', FontAwesomeIcon)