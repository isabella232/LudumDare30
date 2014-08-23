public class Entity
{
	private float        m_x;
	private float        m_y;
	private float        m_radius;

	public float GetX() { return m_x; }
	public float GetY() { return m_y; }
	public float GetRadius() { return m_radius; }
	
	public void SetX(float x) { m_x = x; }
	public void SetY(float y) { m_y = y; }
	public void SetRadius(float radius) { m_radius = radius; }

	public Entity(float x, float y, float radius) 
	{
		m_x = x;
		m_y = y;
		m_radius = radius;
	}

	public void Update(Input input, float delta)
	{
		
	}

	public void Render(RenderContext target)
	{
	}

	public boolean SphereIntersect(Entity other)
	{
		float radiusDistance = m_radius + other.GetRadius();
		float centerDistance = Util.VectorLength(other.GetX() - m_x, other.GetY() - m_y);

		return (centerDistance - radiusDistance) < 0;
	}
}
