import Vue from 'vue'

import { library } from '@fortawesome/fontawesome-svg-core'
import { /* icons you want to import */ } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(
    /* imported icons */ 
)

Vue.component('font-awesome-icon', FontAwesomeIcon)