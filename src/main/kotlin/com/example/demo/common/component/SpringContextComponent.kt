package com.example.demo.common.component

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware


//获取spring boot上下文
class SpringContextComponent : ApplicationContextAware {

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    companion object {
         var context: ApplicationContext? = null

        //        获取bean
        fun getBean(beanName: String): Any {
            return context!!.getBean(beanName)
        }

        //        获取有效profiles
        val activeProfiles: List<String>
            get() = listOf(

            )

        //        获取当前profile
        val currentActiveProfile: String
            get() {
                val profiles = context!!.environment.activeProfiles.toList()
                for (profile in profiles) {
                    if (activeProfiles.any {profile.contains(it,true)}){
                        return profile
                    }
                }
                return ""
            }
    }
}