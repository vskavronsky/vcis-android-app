enum class BuildType {
    Debug,
    Stage,
    Release;

    fun gradleName(): String {
        return when (this) {
            Debug -> "debug"
            Stage -> "stage"
            Release -> "release"
        }
    }
}


data class BuildTypeData constructor(
    val type: BuildType,
    val applicationIdSuffix: String = "",
    val isProGuardEnabled: Boolean = false,
    val isMinifyEnabled: Boolean = false,
    val isShrinkResources: Boolean = false,
    val isCrashlyticsEnabled: Boolean = false,
    val isAnalyticsEnabled: Boolean = false,
    val resValues: List<ResValue> = emptyList(),
    val buildConfigFields: List<BuildConfigField> = emptyList(),
)


data class BuildConfigField constructor(
    val type: String,
    val name: String,
    val value: String
)


data class ResValue constructor(
    val type: String,
    val name: String,
    val value: String,
)
