package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

//https://math.stackexchange.com/questions/1605074/newton-raphson-method-very-slow-convergence
public class NewtonMovingStrategy implements IMovingStrategy{

    @Override
    public void updatePosition(AbsMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        long time = missile.getAge() / 100;

        int dX = (int) (initVelocity *  Math.cos(initAngle) * newtonMethod(7, missile.getMoveCount() / 30 + 1) );
        int dY = (int) (initVelocity * time * Math.sin(initAngle) + (0.5 * MvcGameConfig.GRAVITY /2 * time * time));

        missile.move(new Vector(dX, dY));

    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }



    // f(x) = x^2 - 5
    private static double function(double x) {
        return x * x - 5;
    }

    // f'(x) = 2x
    private static double derivative(double x) {
        return 2 * x;
    }

    public static double newtonMethod(double initialGuess, int iterations) {
        double x = initialGuess;
        double lastStepSize = 0.0;

        for (int i = 0; i < iterations; i++) {
            double fx = function(x);
            double fpx = derivative(x);

            if (fpx == 0) {
                System.out.println("Zero derivative");
                return 0.0;
            }

            double stepSize = fx / fpx;
            x = x - stepSize;

            lastStepSize = Math.abs(stepSize);
        }

        return lastStepSize;
    }

}

//newton approx method that slowly stops the bird as it makes smaller and smaller steps
