public class RenderContext extends Bitmap
{
	public RenderContext(int width, int height)
	{
		super(width, height);
	}

	public void TestDrawImage(Bitmap image, int xStart, int yStart, int width, int height)
	{
		int xEnd = xStart + width;
		int yEnd = yStart + height;

		float imageY = 0;
		float imageYStep = 1.0f/height;
		float imageX = 0;
		float imageXStep = 1.0f/width;
		for(int y = yStart; y < yEnd; y++)
		{
			imageX = 0;
			for(int x = xStart; x < xEnd; x++)
			{
				image.CopyNearest(this, x, y, imageX, imageY);
				imageX += imageXStep;
			}
			imageY += imageYStep;
		}
	}

	public void DrawImage(Bitmap image, float xStart, float yStart, float width, float height)
	{
		float xEnd = xStart + width;
		float yEnd = yStart + height;

		float halfWidth   = GetWidth()/2.0f;
		float halfHeight  = GetHeight()/2.0f;
		float scaleFactor = halfWidth < halfHeight ? halfWidth : halfHeight;

		float imageXStart = 0.0f;
		float imageYStart = 0.0f;
		float imageYStep  = 1.0f/(((yEnd * scaleFactor) + halfHeight)
			   	-((yStart * scaleFactor) + halfHeight));
		float imageXStep  = 1.0f/(((xEnd * scaleFactor) + halfWidth)
			   	-((xStart * scaleFactor) + halfWidth));

		if(xStart < -1.0f)
		{
			imageXStart = -((xStart + 1.0f)/(xEnd - xStart));
			xStart = -1.0f;
		}
		if(xStart > 1.0f)
		{
			imageXStart = -((xStart + 1.0f)/(xEnd - xStart));
			xStart = 1.0f;
		}
		if(yStart < -1.0f)
		{
			imageXStart = -((yStart + 1.0f)/(yEnd - yStart));
			yStart = -1.0f;
		}
		if(yStart > 1.0f)
		{
			imageXStart = -((yStart + 1.0f)/(yEnd - yStart));
			yStart = 1.0f;
		}

		xEnd = Util.Clamp(xEnd, -1.0f, 1.0f);
		yEnd = Util.Clamp(yEnd, -1.0f, 1.0f);
		
		xStart = (xStart * scaleFactor) + halfWidth;
		yStart = (yStart * scaleFactor) + halfHeight;
		xEnd   = (xEnd * scaleFactor) + halfWidth;
		yEnd   = (yEnd * scaleFactor) + halfHeight;

		float imageY = imageYStart;

		for(int y = (int)yStart; y < (int)yEnd; y++)
		{
			float imageX = imageXStart;
			for(int x = (int)xStart; x < (int)xEnd; x++)
			{
				image.CopyNearest(this, x, y, imageX, imageY);
				imageX += imageXStep;
			}
			imageY += imageYStep;
		}
	}

//	public void FillRect(int x, int y, int width, int height,
//			byte a, byte b, byte g, byte r)
//	{
//		for(int j = y; j < y + height; j++)
//		{
//			for(int i = x; i < x + width; i++)
//			{
//				DrawPixel(i, j, a, b, g, r);
//			}
//		}
//	}
}
