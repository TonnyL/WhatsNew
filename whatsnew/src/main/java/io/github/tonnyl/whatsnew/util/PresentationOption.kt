package io.github.tonnyl.whatsnew.util

/**
 * Created by lizhaotailang on 30/11/2017.
 */
enum class PresentationOption {

    // Ignore the change of version name and version code, present always.
    DEBUG,

    // Check both of version code number and version name.
    // Try to split the version name to numbers firstly.
    // Check if one of the first two elements is higher than previous one.
    // If positive, present.
    // Or then check the version code like option [ALWAYS].
    IF_NEEDED,

    // Just check the version code. Present if the version code number is
    // higher than the previous one.
    ALWAYS,

    // Never present.
    NEVER

}