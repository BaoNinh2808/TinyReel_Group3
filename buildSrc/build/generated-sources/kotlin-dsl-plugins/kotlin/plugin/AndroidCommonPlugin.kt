package plugin


/**
 * Precompiled [android-common.gradle.kts][plugin.Android_common_gradle] script plugin.
 *
 * @see plugin.Android_common_gradle
 */
public
class AndroidCommonPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("plugin.Android_common_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
