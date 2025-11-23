val BuildTypes = buildList {
    this += BuildTypeData(
        type = BuildType.Debug,
        applicationIdSuffix = ".dev",
        resValues = buildList {
            this += ResValue(
                type = "string",
                name = "app_name",
                value = "Dev App Name",
            )
        },
        buildConfigFields = buildList {
            this += BuildConfigField(
                type = "String",
                name = "BASE_API_URL",
                value = "\"https://dev.api.example.com/\""
            )
        }
    )

    this += BuildTypeData(
        type = BuildType.Stage,
        applicationIdSuffix = ".stage",
        isAnalyticsEnabled = true,
        isCrashlyticsEnabled = true,
        resValues = buildList {
            this += ResValue(
                type = "string",
                name = "app_name",
                value = "Stage App Name",
            )
        },
        buildConfigFields = buildList {
            this += BuildConfigField(
                type = "String",
                name = "BASE_API_URL",
                value = "\"https://dev.api.example.com/\""
            )
        }
    )

    this += BuildTypeData(
        type = BuildType.Release,
        isProGuardEnabled = true,
        isMinifyEnabled = true,
        isShrinkResources = true,
        isAnalyticsEnabled = true,
        resValues = buildList {
            this += ResValue(
                type = "string",
                name = "app_name",
                value = "Release App Name",
            )
        },
        buildConfigFields = buildList {
            this += BuildConfigField(
                type = "String",
                name = "BASE_API_URL",
                value = "\"https://api.example.com/\""
            )
        }
    )
}