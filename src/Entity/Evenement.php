<?php

namespace App\Entity;

use App\Repository\EvenementRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=EvenementRepository::class)
 */
class Evenement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom_evenement;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\Column(type="integer")
     */
    private $nombre_de_participants;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $qr_code;

    /**
     * @ORM\Column(type="integer")
     */
    private $billet;

    /**
     * @ORM\Column(type="date")
     */
    private $date_de_evenement;

    /**
     * @ORM\Column(type="integer")
     */
    private $collaborateur1_id;

    /**
     * @ORM\OneToOne(targetEntity=Visite::class, mappedBy="evenement", cascade={"persist", "remove"})
     */
    private $visite;
     

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomEvenement(): ?string
    {
        return $this->nom_evenement;
    }

    public function setNomEvenement(string $nom_evenement): self
    {
        $this->nom_evenement = $nom_evenement;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getNombreDeParticipants(): ?int
    {
        return $this->nombre_de_participants;
    }

    public function setNombreDeParticipants(int $nombre_de_participants): self
    {
        $this->nombre_de_participants = $nombre_de_participants;

        return $this;
    }

    public function getQrCode(): ?string
    {
        return $this->qr_code;
    }

    public function setQrCode(string $qr_code): self
    {
        $this->qr_code = $qr_code;

        return $this;
    }

    public function getBillet(): ?int
    {
        return $this->billet;
    }

    public function setBillet(int $billet): self
    {
        $this->billet = $billet;

        return $this;
    }

    public function getDateDeEvenement(): ?\DateTimeInterface
    {
        return $this->date_de_evenement;
    }

    public function setDateDeEvenement(\DateTimeInterface $date_de_evenement): self
    {
        $this->date_de_evenement = $date_de_evenement;

        return $this;
    }

    public function getCollaborateur1Id(): ?int
    {
        return $this->collaborateur1_id;
    }

    public function setCollaborateur1Id(int $collaborateur1_id): self
    {
        $this->collaborateur1_id = $collaborateur1_id;

        return $this;
    }
    public function __toString(){
        return $this->description;
        }

    public function getVisite(): ?Visite
    {
        return $this->visite;
    }

    public function setVisite(?Visite $visite): self
    {
        // unset the owning side of the relation if necessary
        if ($visite === null && $this->visite !== null) {
            $this->visite->setEvenement(null);
        }

        // set the owning side of the relation if necessary
        if ($visite !== null && $visite->getEvenement() !== $this) {
            $visite->setEvenement($this);
        }

        $this->visite = $visite;

        return $this;
    }

}
