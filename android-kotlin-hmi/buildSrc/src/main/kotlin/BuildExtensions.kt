import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

val Project.versionCode: Int?
    get() = obtainPropertyBy<String>("versionCode")?.toInt()

val Project.versionName: String?
    get() = obtainPropertyBy<String>("versionName")


inline fun <reified T : Any> Project.obtainPropertyBy(name: String): T? {
    return when {
        hasProperty(name) -> property(name) as T
        else -> null
    }
}

inline fun <reified T> NamedDomainObjectContainer<T>.ensureBuildTypeBy(name: String, closure: T.() -> Unit = {}) {
    closure(findByName(name) ?: create(name))
}
