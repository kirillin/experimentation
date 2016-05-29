package kz.epam.airline.aircrafts.classE;

public class TransportHelicopter extends ClassE
{
	private int maxVolumePayload;
	private int payloadCapacity;
	

	public int getMaxVolumePayload()
	{
		return this.maxVolumePayload;
	}
	public void setMaxVolumePayload(int aMaxVolumePayload)
	{
		this.maxVolumePayload = aMaxVolumePayload;
	}

	public int getPayloadCapacity()
	{
		return this.payloadCapacity;
	}
	public void setPayloadCapacity(int aPayloadCapacity)
	{
		this.payloadCapacity = aPayloadCapacity;
	}
}
