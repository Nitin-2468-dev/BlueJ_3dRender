import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Arrays;

/**
 * Z‑Buffer demo with per‑vertex color interpolation (Gouraud shading).
 */
public class ZBufferGradientDemo extends JPanel {
  private final int W, H;
  private BufferedImage frame;
  private double[][] zBuf;

  public ZBufferGradientDemo(int w, int h) {
    W = w; H = h;
    frame = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
    zBuf  = new double[W][H];
    setPreferredSize(new Dimension(W, H));
  }

  @Override
  protected void paintComponent(Graphics gg) {
    super.paintComponent(gg);
    clearBuffers();

    // Single triangle with RGB at vertices
    int[] xs = { 100, 300, 150 };
    int[] ys = { 100, 100, 300 };
    double[] zs = { 0.2, 0.5, 0.8 }; // arbitrary depths
    Color[] cs  = { Color.RED, Color.GREEN, Color.BLUE };

    rasterTriGradient(xs, ys, zs, cs);

    gg.drawImage(frame, 0, 0, null);
  }

  private void clearBuffers() {
    Graphics2D g2 = frame.createGraphics();
    g2.setColor(getBackground());
    g2.fillRect(0, 0, W, H);
    g2.dispose();
    for (int x = 0; x < W; x++) {
      Arrays.fill(zBuf[x], Double.POSITIVE_INFINITY);
    }
  }

  /**
   * Rasterizes one triangle with interpolated vertex colors.
   */
  private void rasterTriGradient(int[] xs, int[] ys, double[] zs, Color[] cs) {
    int xmin = Math.max(0,   Math.min(xs[0], Math.min(xs[1], xs[2])));
    int xmax = Math.min(W-1, Math.max(xs[0], Math.max(xs[1], xs[2])));
    int ymin = Math.max(0,   Math.min(ys[0], Math.min(ys[1], ys[2])));
    int ymax = Math.min(H-1, Math.max(ys[0], Math.max(ys[1], ys[2])));
    double area = edge(xs[0], ys[0], xs[1], ys[1], xs[2], ys[2]);

    for (int y = ymin; y <= ymax; y++) {
      for (int x = xmin; x <= xmax; x++) {
        double w0 = edge(xs[1], ys[1], xs[2], ys[2], x, y) / area;
        double w1 = edge(xs[2], ys[2], xs[0], ys[0], x, y) / area;
        double w2 = edge(xs[0], ys[0], xs[1], ys[1], x, y) / area;
        if (w0 >= 0 && w1 >= 0 && w2 >= 0) {
          double z = w0*zs[0] + w1*zs[1] + w2*zs[2];
          if (z < zBuf[x][y]) {
            zBuf[x][y] = z;
            // interpolate color
            int r = clamp((int)(w0*cs[0].getRed()   + w1*cs[1].getRed()   + w2*cs[2].getRed()));
            int g = clamp((int)(w0*cs[0].getGreen() + w1*cs[1].getGreen() + w2*cs[2].getGreen()));
            int b = clamp((int)(w0*cs[0].getBlue()  + w1*cs[1].getBlue()  + w2*cs[2].getBlue()));
            int rgb = (0xFF<<24) | (r<<16) | (g<<8) | b;
            frame.setRGB(x, y, rgb);
          }
        }
      }
    }
  }

  // helper to clamp 0..255
  private int clamp(int v) {
    return v<0?0:(v>255?255:v);
  }

  // edge function: twice signed area of (a->b->c)
  private double edge(int ax,int ay,int bx,int by,int cx,int cy) {
    return (cx-ax)*(by-ay) - (cy-ay)*(bx-ax);
  }

  public static void main(String[] args) {
    JFrame f = new JFrame("Z‑Buffer Gradient Demo");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(new ZBufferGradientDemo(400, 400));
    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }
}
