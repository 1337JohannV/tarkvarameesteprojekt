import Vue from 'vue'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faPlay, faSignOutAlt, faBars, faList, faSearch, faEye, faRedo } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faPlay, faSignOutAlt, faBars, faList, faSearch, faEye, faRedo
)

Vue.component('font-awesome-icon', FontAwesomeIcon)