package frc.team4909.robot.util;

public class Util{
 /**
 * map a number from one range to another
 * @param  {num} value   the value to be mapped
 * @param  {num} old_min the minimum of value
 * @param  {num} old_max the maximum of value
 * @param  {num} new_min the new minimum value
 * @param  {num} new_max the new maximum value
 * @return The value remaped on the range [new_min new_max]
 */
    public static double map(double value, double old_min, double old_max, double new_min, double new_max) {
        return (value - old_min) / (old_max - old_min) * (new_max - new_min) + new_min;
    }
}