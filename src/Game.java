import java.awt.event.KeyEvent;

public class Game
{
	public Game()
	{
		x = -0.1f;
		y = -0.1f;
		image = new Bitmap(100, 100);
		image.ClearScreen((byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFF);
	}

	public void Update(Input input, float delta)
	{
		float speed = 1.0f;
		if(input.GetKey(KeyEvent.VK_W))
		{
			y -= delta * speed;
		}
		if(input.GetKey(KeyEvent.VK_S))
		{
			y += delta * speed;
		}
		if(input.GetKey(KeyEvent.VK_D))
		{
			x += delta * speed;
		}
		if(input.GetKey(KeyEvent.VK_A))
		{
			x -= delta * speed;
		}
	}

	float x;
	float y;
	Bitmap image;

	public void Render(RenderContext target)
	{
		target.Clear((byte)0x00);
		target.DrawImage(image, x, y, 0.2f, 0.2f);
	}
}
